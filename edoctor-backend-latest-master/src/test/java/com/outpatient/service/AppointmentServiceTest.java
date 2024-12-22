package com.outpatient.service;

import static org.junit.jupiter.api.Assertions.assertNull;

import com.outpatient.entity.Appointment;
import com.outpatient.repository.AppointmentRepository;
import com.outpatient.repository.DoctorRepository;
import com.outpatient.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AppointmentService.class, AppointmentRepository.class, DoctorRepository.class,
        UserRepository.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class AppointmentServiceDiffblueTest {
    @MockBean
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentService appointmentService;

    @MockBean
    private DoctorRepository doctorRepository;

    @MockBean
    private UserRepository userRepository;

    /**
     * Test
     * {@link AppointmentService#scheduleAppointment(String, String, LocalDateTime)}.
     * <p>
     * Method under test:
     * {@link AppointmentService#scheduleAppointment(String, String, LocalDateTime)}
     */
    @Test
    @DisplayName("Test scheduleAppointment(String, String, LocalDateTime)")
    @Disabled("TODO: Complete this test")
    void testScheduleAppointment() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@31370a23 testClass = com.outpatient.service.DiffblueFakeClass112, locations = [], classes = [com.outpatient.service.AppointmentService, com.outpatient.repository.AppointmentRepository, com.outpatient.repository.DoctorRepository, com.outpatient.repository.UserRepository], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@4cfc0c09, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@38665774, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@0, org.springframework.boot.test.web.reactor.netty.DisableReactorResourceFactoryGlobalResourcesContextCustomizerFactory$DisableReactorResourceFactoryGlobalResourcesContextCustomizerCustomizer@3e859fa3, org.springframework.boot.test.autoconfigure.OnFailureConditionReportContextCustomizerFactory$OnFailureConditionReportContextCustomizer@2afc0c1f, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@761e4b09, org.springframework.test.context.support.DynamicPropertiesContextCustomizer@0], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:215)
        //       at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1709)
        //       at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:570)
        //       at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
        //       at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
        //       at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:727)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange and Act
        appointmentService.scheduleAppointment("Doctor Name", "janedoe", LocalDate.of(1970, 1, 1).atStartOfDay());
    }

    /**
     * Test {@link AppointmentService#getAppointmentsByDoctorName(String)}.
     * <p>
     * Method under test:
     * {@link AppointmentService#getAppointmentsByDoctorName(String)}
     */
    @Test
    @DisplayName("Test getAppointmentsByDoctorName(String)")
    void testGetAppointmentsByDoctorName() {
        // Arrange and Act
        List<Appointment> actualAppointmentsByDoctorName = appointmentService.getAppointmentsByDoctorName("Doctor Name");

        // Assert
        assertNull(actualAppointmentsByDoctorName);
    }

    /**
     * Test {@link AppointmentService#getAppointmentsByDoctorUsername(String)}.
     * <p>
     * Method under test:
     * {@link AppointmentService#getAppointmentsByDoctorUsername(String)}
     */
    @Test
    @DisplayName("Test getAppointmentsByDoctorUsername(String)")
    @Disabled("TODO: Complete this test")
    void testGetAppointmentsByDoctorUsername() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@2ba233a9 testClass = com.outpatient.service.DiffblueFakeClass110, locations = [], classes = [com.outpatient.service.AppointmentService, com.outpatient.repository.AppointmentRepository, com.outpatient.repository.DoctorRepository, com.outpatient.repository.UserRepository], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@4cfc0c09, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@38665774, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@0, org.springframework.boot.test.web.reactor.netty.DisableReactorResourceFactoryGlobalResourcesContextCustomizerFactory$DisableReactorResourceFactoryGlobalResourcesContextCustomizerCustomizer@3e859fa3, org.springframework.boot.test.autoconfigure.OnFailureConditionReportContextCustomizerFactory$OnFailureConditionReportContextCustomizer@2afc0c1f, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@761e4b09, org.springframework.test.context.support.DynamicPropertiesContextCustomizer@0], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:215)
        //       at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1709)
        //       at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:570)
        //       at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
        //       at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
        //       at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:727)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange and Act
        appointmentService.getAppointmentsByDoctorUsername("janedoe");
    }

    /**
     * Test {@link AppointmentService#getAppointmentsByUsername(String)}.
     * <p>
     * Method under test:
     * {@link AppointmentService#getAppointmentsByUsername(String)}
     */
    @Test
    @DisplayName("Test getAppointmentsByUsername(String)")
    @Disabled("TODO: Complete this test")
    void testGetAppointmentsByUsername() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@7b9a0718 testClass = com.outpatient.service.DiffblueFakeClass111, locations = [], classes = [com.outpatient.service.AppointmentService, com.outpatient.repository.AppointmentRepository, com.outpatient.repository.DoctorRepository, com.outpatient.repository.UserRepository], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@4cfc0c09, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@38665774, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@0, org.springframework.boot.test.web.reactor.netty.DisableReactorResourceFactoryGlobalResourcesContextCustomizerFactory$DisableReactorResourceFactoryGlobalResourcesContextCustomizerCustomizer@3e859fa3, org.springframework.boot.test.autoconfigure.OnFailureConditionReportContextCustomizerFactory$OnFailureConditionReportContextCustomizer@2afc0c1f, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@761e4b09, org.springframework.test.context.support.DynamicPropertiesContextCustomizer@0], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:215)
        //       at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1709)
        //       at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:570)
        //       at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
        //       at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
        //       at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:727)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange and Act
        appointmentService.getAppointmentsByUsername("janedoe");
    }
}
