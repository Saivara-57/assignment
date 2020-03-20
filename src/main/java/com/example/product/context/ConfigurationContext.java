package com.example.product.context;

import lombok.Getter;

/**
 * Builder to construct context for tenant based configurations for formatting so that the same construction process
 * can create different representations.
 *
 * @author Kaustov Sarmah
 */
@Getter
public class ConfigurationContext {

    private String user;
    private String tenant;
    private String templateName;
    private String templatePath;

    private ConfigurationContext(ConfigurationContext.ConfigurationContextBuilder builder) {
        this.user = builder.user;
        this.tenant = builder.tenant;
        this.templateName = builder.templateName;
    }

    public static class ConfigurationContextBuilder {
        private String user;
        private String tenant;
        private String templateName;
        private String templatePath;

        public ConfigurationContextBuilder(String user) {
            this.user = user;
        }

        public ConfigurationContext.ConfigurationContextBuilder withTenantName(String tenant) {
            this.tenant = tenant;

            return this;
        }

        public ConfigurationContext.ConfigurationContextBuilder withTemplateName(
                String templateName) {
            this.templateName = templateName;

            return this;
        }

        public ConfigurationContext.ConfigurationContextBuilder withTemplatePath(
                String templatePath) {
            this.templatePath = templatePath;

            return this;
        }
        public ConfigurationContext build() {
            return new ConfigurationContext(this);
        }
    }
}
