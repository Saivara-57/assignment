package com.example.product.context;

import lombok.Getter;

/**
 * Builder to construct context for formatting so that the same construction process can create different
 * representations.
 *
 * @author Kaustov Sarmah
 */
@Getter
public class FormattingContext {

    private String flightId;
    private String userId;
    private String transaction;
    private String format;
    private String formatType;
    private String contentType;
    private String encode;

    private FormattingContext(FormattingContextBuilder builder) {
        this.flightId = builder.flightId;
        this.userId = builder.userId;
        this.transaction = builder.transaction;
        this.format = builder.format;
        this.formatType = builder.formatType;
        this.contentType = builder.contentType;
        this.encode = builder.encode;
    }

    public static class FormattingContextBuilder {
        private String flightId;
        private String userId;
        private String transaction;
        private String format;
        private String formatType;
        private String contentType;
        private String encode;

        public FormattingContextBuilder(String userId) {
            this.userId = userId;
        }

        public FormattingContextBuilder withFlightId(String flightId) {
            this.flightId = flightId;

            return this;
        }

        public FormattingContextBuilder withTransaction(String transaction) {
            this.transaction = transaction;

            return this;
        }

        public FormattingContextBuilder withFormat(String format) {
            this.format = format;

            return this;
        }

        public FormattingContextBuilder withFormatType(String formatType) {
            this.formatType = formatType;

            return this;
        }

        public FormattingContextBuilder withContentType(String contentType) {
            this.contentType = contentType;

            return this;
        }

        public FormattingContextBuilder withEncode(String encode) {
            this.encode = encode;

            return this;
        }

        public FormattingContext build() {
            return new FormattingContext(this);
        }
    }
}
