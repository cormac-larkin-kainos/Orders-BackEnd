package org.kainos.ea;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.kainos.ea.resources.CustomerController;
import org.kainos.ea.resources.ProductController;

public class DropWizardOrdersAPIApplication extends Application<DropWizardOrdersAPIConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DropWizardOrdersAPIApplication().run(args);
    }

    @Override
    public String getName() {
        return "DropWizardOrdersAPI";
    }

    @Override
    public void initialize(final Bootstrap<DropWizardOrdersAPIConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<DropWizardOrdersAPIConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(DropWizardOrdersAPIConfiguration dropWizardOrdersAPIApplication) {
                return dropWizardOrdersAPIApplication.getSwagger();
            }
        });
    }

    @Override
    public void run(final DropWizardOrdersAPIConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(new CustomerController());
        environment.jersey().register(new ProductController());
    }

}
