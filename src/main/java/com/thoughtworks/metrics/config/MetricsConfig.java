package com.thoughtworks.metrics.config;

import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import com.codahale.metrics.jvm.MemoryUsageGaugeSet;
import com.codahale.metrics.jvm.ThreadStatesGaugeSet;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableMetrics
public class MetricsConfig extends MetricsConfigurerAdapter {

    @Override
    public void configureReporters(MetricRegistry metricRegistry) {
        Graphite graphite = new Graphite(new InetSocketAddress("192.168.99.100", 2003));
        GraphiteReporter graphiteReporter = GraphiteReporter.forRegistry(metricRegistry)
                .prefixedWith("juntao-laptop")
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .filter(MetricFilter.ALL)
                .build(graphite);

//        ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(metricRegistry).build();
//        registerReporter(consoleReporter);
//        consoleReporter.start(1, TimeUnit.MINUTES);

        registerReporter(graphiteReporter);
        graphiteReporter.start(1, TimeUnit.MINUTES);


        metricRegistry.registerAll(new MemoryUsageGaugeSet());
        metricRegistry.registerAll(new ThreadStatesGaugeSet());


    }

}
