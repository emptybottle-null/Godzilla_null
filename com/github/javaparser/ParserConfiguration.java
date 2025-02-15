/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser;

import com.github.javaparser.CommentsInserter;
import com.github.javaparser.LineEndingProcessingProvider;
import com.github.javaparser.ParseResult;
import com.github.javaparser.Problem;
import com.github.javaparser.Provider;
import com.github.javaparser.Providers;
import com.github.javaparser.Range;
import com.github.javaparser.UnicodeEscapeProcessingProvider;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.validator.ProblemReporter;
import com.github.javaparser.ast.validator.Validator;
import com.github.javaparser.ast.validator.language_level_validations.Java10PreviewValidator;
import com.github.javaparser.ast.validator.language_level_validations.Java10Validator;
import com.github.javaparser.ast.validator.language_level_validations.Java11PreviewValidator;
import com.github.javaparser.ast.validator.language_level_validations.Java11Validator;
import com.github.javaparser.ast.validator.language_level_validations.Java12PreviewValidator;
import com.github.javaparser.ast.validator.language_level_validations.Java12Validator;
import com.github.javaparser.ast.validator.language_level_validations.Java13PreviewValidator;
import com.github.javaparser.ast.validator.language_level_validations.Java13Validator;
import com.github.javaparser.ast.validator.language_level_validations.Java14PreviewValidator;
import com.github.javaparser.ast.validator.language_level_validations.Java14Validator;
import com.github.javaparser.ast.validator.language_level_validations.Java15PreviewValidator;
import com.github.javaparser.ast.validator.language_level_validations.Java15Validator;
import com.github.javaparser.ast.validator.language_level_validations.Java16PreviewValidator;
import com.github.javaparser.ast.validator.language_level_validations.Java16Validator;
import com.github.javaparser.ast.validator.language_level_validations.Java1_0Validator;
import com.github.javaparser.ast.validator.language_level_validations.Java1_1Validator;
import com.github.javaparser.ast.validator.language_level_validations.Java1_2Validator;
import com.github.javaparser.ast.validator.language_level_validations.Java1_3Validator;
import com.github.javaparser.ast.validator.language_level_validations.Java1_4Validator;
import com.github.javaparser.ast.validator.language_level_validations.Java5Validator;
import com.github.javaparser.ast.validator.language_level_validations.Java6Validator;
import com.github.javaparser.ast.validator.language_level_validations.Java7Validator;
import com.github.javaparser.ast.validator.language_level_validations.Java8Validator;
import com.github.javaparser.ast.validator.language_level_validations.Java9Validator;
import com.github.javaparser.ast.validator.postprocessors.Java10PostProcessor;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;
import com.github.javaparser.resolution.SymbolResolver;
import com.github.javaparser.utils.LineSeparator;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ParserConfiguration {
    private boolean detectOriginalLineSeparator = true;
    private boolean storeTokens = true;
    private boolean attributeComments = true;
    private boolean doNotAssignCommentsPrecedingEmptyLines = true;
    private boolean ignoreAnnotationsWhenAttributingComments = false;
    private boolean lexicalPreservationEnabled = false;
    private boolean preprocessUnicodeEscapes = false;
    private SymbolResolver symbolResolver = null;
    private int tabSize = 1;
    private LanguageLevel languageLevel = LanguageLevel.JAVA_8;
    private Charset characterEncoding = Providers.UTF8;
    private final List<Providers.PreProcessor> preProcessors = new ArrayList<Providers.PreProcessor>();
    private final List<ParseResult.PostProcessor> postProcessors = new ArrayList<ParseResult.PostProcessor>();

    public ParserConfiguration() {
        class UnicodeEscapeProcessor
        implements Providers.PreProcessor,
        ParseResult.PostProcessor {
            private UnicodeEscapeProcessingProvider _unicodeDecoder;

            UnicodeEscapeProcessor() {
            }

            @Override
            public Provider process(Provider innerProvider) {
                if (ParserConfiguration.this.isPreprocessUnicodeEscapes()) {
                    this._unicodeDecoder = new UnicodeEscapeProcessingProvider(innerProvider);
                    return this._unicodeDecoder;
                }
                return innerProvider;
            }

            @Override
            public void process(ParseResult<? extends Node> result, ParserConfiguration configuration) {
                if (ParserConfiguration.this.isPreprocessUnicodeEscapes()) {
                    result.getResult().ifPresent(root -> {
                        UnicodeEscapeProcessingProvider.PositionMapping mapping = this._unicodeDecoder.getPositionMapping();
                        if (!mapping.isEmpty()) {
                            root.walk(node -> node.getRange().ifPresent(range -> node.setRange(mapping.transform((Range)range))));
                        }
                    });
                }
            }
        }
        UnicodeEscapeProcessor unicodeProcessor = new UnicodeEscapeProcessor();
        this.preProcessors.add(unicodeProcessor);
        this.postProcessors.add(unicodeProcessor);
        class LineEndingProcessor
        implements Providers.PreProcessor,
        ParseResult.PostProcessor {
            private LineEndingProcessingProvider _lineEndingProcessingProvider;

            LineEndingProcessor() {
            }

            @Override
            public Provider process(Provider innerProvider) {
                if (ParserConfiguration.this.isDetectOriginalLineSeparator()) {
                    this._lineEndingProcessingProvider = new LineEndingProcessingProvider(innerProvider);
                    return this._lineEndingProcessingProvider;
                }
                return innerProvider;
            }

            @Override
            public void process(ParseResult<? extends Node> result, ParserConfiguration configuration) {
                if (ParserConfiguration.this.isDetectOriginalLineSeparator()) {
                    result.getResult().ifPresent(rootNode -> {
                        LineSeparator detectedLineSeparator = this._lineEndingProcessingProvider.getDetectedLineEnding();
                        rootNode.setData(Node.LINE_SEPARATOR_KEY, detectedLineSeparator);
                    });
                }
            }
        }
        LineEndingProcessor lineEndingProcessor = new LineEndingProcessor();
        this.preProcessors.add(lineEndingProcessor);
        this.postProcessors.add(lineEndingProcessor);
        this.postProcessors.add((result, configuration) -> {
            if (configuration.isAttributeComments()) {
                result.ifSuccessful(resultNode -> result.getCommentsCollection().ifPresent(comments -> new CommentsInserter(configuration).insertComments((Node)resultNode, comments.copy().getComments())));
            }
        });
        this.postProcessors.add((result, configuration) -> {
            LanguageLevel languageLevel = this.getLanguageLevel();
            if (languageLevel != null) {
                if (languageLevel.postProcessor != null) {
                    languageLevel.postProcessor.process(result, configuration);
                }
                if (languageLevel.validator != null) {
                    languageLevel.validator.accept((Node)result.getResult().get(), new ProblemReporter(newProblem -> result.getProblems().add((Problem)newProblem)));
                }
            }
        });
        this.postProcessors.add((result, configuration) -> configuration.getSymbolResolver().ifPresent(symbolResolver -> result.ifSuccessful(resultNode -> {
            if (resultNode instanceof CompilationUnit) {
                resultNode.setData(Node.SYMBOL_RESOLVER_KEY, symbolResolver);
            }
        })));
        this.postProcessors.add((result, configuration) -> {
            if (configuration.isLexicalPreservationEnabled()) {
                result.ifSuccessful(LexicalPreservingPrinter::setup);
            }
        });
    }

    public boolean isAttributeComments() {
        return this.attributeComments;
    }

    public ParserConfiguration setAttributeComments(boolean attributeComments) {
        this.attributeComments = attributeComments;
        return this;
    }

    public boolean isDoNotAssignCommentsPrecedingEmptyLines() {
        return this.doNotAssignCommentsPrecedingEmptyLines;
    }

    public ParserConfiguration setDoNotAssignCommentsPrecedingEmptyLines(boolean doNotAssignCommentsPrecedingEmptyLines) {
        this.doNotAssignCommentsPrecedingEmptyLines = doNotAssignCommentsPrecedingEmptyLines;
        return this;
    }

    public boolean isIgnoreAnnotationsWhenAttributingComments() {
        return this.ignoreAnnotationsWhenAttributingComments;
    }

    public ParserConfiguration setIgnoreAnnotationsWhenAttributingComments(boolean ignoreAnnotationsWhenAttributingComments) {
        this.ignoreAnnotationsWhenAttributingComments = ignoreAnnotationsWhenAttributingComments;
        return this;
    }

    public ParserConfiguration setStoreTokens(boolean storeTokens) {
        this.storeTokens = storeTokens;
        if (!storeTokens) {
            this.setAttributeComments(false);
        }
        return this;
    }

    public boolean isStoreTokens() {
        return this.storeTokens;
    }

    public int getTabSize() {
        return this.tabSize;
    }

    public ParserConfiguration setTabSize(int tabSize) {
        this.tabSize = tabSize;
        return this;
    }

    public ParserConfiguration setLexicalPreservationEnabled(boolean lexicalPreservationEnabled) {
        this.lexicalPreservationEnabled = lexicalPreservationEnabled;
        return this;
    }

    public boolean isLexicalPreservationEnabled() {
        return this.lexicalPreservationEnabled;
    }

    public Optional<SymbolResolver> getSymbolResolver() {
        return Optional.ofNullable(this.symbolResolver);
    }

    public ParserConfiguration setSymbolResolver(SymbolResolver symbolResolver) {
        this.symbolResolver = symbolResolver;
        return this;
    }

    public List<Providers.PreProcessor> getPreProcessors() {
        return this.preProcessors;
    }

    public List<ParseResult.PostProcessor> getPostProcessors() {
        return this.postProcessors;
    }

    public ParserConfiguration setLanguageLevel(LanguageLevel languageLevel) {
        this.languageLevel = languageLevel;
        return this;
    }

    public LanguageLevel getLanguageLevel() {
        return this.languageLevel;
    }

    public ParserConfiguration setPreprocessUnicodeEscapes(boolean preprocessUnicodeEscapes) {
        this.preprocessUnicodeEscapes = preprocessUnicodeEscapes;
        return this;
    }

    public boolean isPreprocessUnicodeEscapes() {
        return this.preprocessUnicodeEscapes;
    }

    public ParserConfiguration setDetectOriginalLineSeparator(boolean detectOriginalLineSeparator) {
        this.detectOriginalLineSeparator = detectOriginalLineSeparator;
        return this;
    }

    public boolean isDetectOriginalLineSeparator() {
        return this.detectOriginalLineSeparator;
    }

    public Charset getCharacterEncoding() {
        return this.characterEncoding;
    }

    public ParserConfiguration setCharacterEncoding(Charset characterEncoding) {
        this.characterEncoding = characterEncoding;
        return this;
    }

    public static enum LanguageLevel {
        JAVA_1_0(new Java1_0Validator(), null),
        JAVA_1_1(new Java1_1Validator(), null),
        JAVA_1_2(new Java1_2Validator(), null),
        JAVA_1_3(new Java1_3Validator(), null),
        JAVA_1_4(new Java1_4Validator(), null),
        JAVA_5(new Java5Validator(), null),
        JAVA_6(new Java6Validator(), null),
        JAVA_7(new Java7Validator(), null),
        JAVA_8(new Java8Validator(), null),
        JAVA_9(new Java9Validator(), null),
        JAVA_10(new Java10Validator(), new Java10PostProcessor()),
        JAVA_10_PREVIEW(new Java10PreviewValidator(), new Java10PostProcessor()),
        JAVA_11(new Java11Validator(), null),
        JAVA_11_PREVIEW(new Java11PreviewValidator(), null),
        JAVA_12(new Java12Validator(), null),
        JAVA_12_PREVIEW(new Java12PreviewValidator(), null),
        JAVA_13(new Java13Validator(), null),
        JAVA_13_PREVIEW(new Java13PreviewValidator(), null),
        JAVA_14(new Java14Validator(), null),
        JAVA_14_PREVIEW(new Java14PreviewValidator(), null),
        JAVA_15(new Java15Validator(), null),
        JAVA_15_PREVIEW(new Java15PreviewValidator(), null),
        JAVA_16(new Java16Validator(), null),
        JAVA_16_PREVIEW(new Java16PreviewValidator(), null);

        public static LanguageLevel RAW;
        public static LanguageLevel POPULAR;
        public static LanguageLevel CURRENT;
        public static LanguageLevel BLEEDING_EDGE;
        final Validator validator;
        final ParseResult.PostProcessor postProcessor;
        private static final LanguageLevel[] yieldSupport;

        private LanguageLevel(Validator validator, ParseResult.PostProcessor postProcessor) {
            this.validator = validator;
            this.postProcessor = postProcessor;
        }

        public boolean isYieldSupported() {
            return Arrays.stream(yieldSupport).anyMatch(level -> level == this);
        }

        static {
            RAW = null;
            POPULAR = JAVA_8;
            CURRENT = JAVA_15;
            BLEEDING_EDGE = JAVA_16_PREVIEW;
            yieldSupport = new LanguageLevel[]{JAVA_13, JAVA_13_PREVIEW, JAVA_14, JAVA_14_PREVIEW, JAVA_15, JAVA_15_PREVIEW, JAVA_16, JAVA_16_PREVIEW};
        }
    }
}

