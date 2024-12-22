package com.outpatient.service;

import static org.junit.jupiter.api.Assertions.assertSame;

import com.outpatient.entity.Doctor;
import com.outpatient.entity.DoctorAvailability;
import com.outpatient.repository.DoctorAvailabilityRepository;

import java.time.LocalTime;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DoctorAvailabilityService.class, DoctorAvailabilityRepository.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class DoctorAvailabilityServiceDiffblueTest {
    @MockBean
    private DoctorAvailabilityRepository doctorAvailabilityRepository;

    @Autowired
    private DoctorAvailabilityService doctorAvailabilityService;

    /**
     * Test {@link DoctorAvailabilityService#addAvailability(DoctorAvailability)}.
     * <p>
     * Method under test:
     * {@link DoctorAvailabilityService#addAvailability(DoctorAvailability)}
     */
    @Test
    @DisplayName("Test addAvailability(DoctorAvailability)")
    void testAddAvailability() {
        // Arrange
        Doctor doctor = new Doctor();
        doctor.setAvailable(true);
        doctor.setId(1L);
        doctor.setName("Name");
        doctor.setSpecialization("Specialization");
        doctor.setUsername("janedoe");

        DoctorAvailability doctorAvailability = new DoctorAvailability();
        doctorAvailability.setDay(DoctorAvailability.Day.MONDAY);
        doctorAvailability.setDoctor(doctor);
        doctorAvailability.setEndTime(LocalTime.MIDNIGHT);
        doctorAvailability.setId(1L);
        doctorAvailability.setStartTime(LocalTime.MIDNIGHT);

        Doctor doctor2 = new Doctor();
        doctor2.setAvailable(true);
        doctor2.setId(1L);
        doctor2.setName("Name");
        doctor2.setSpecialization("Specialization");
        doctor2.setUsername("janedoe");

        DoctorAvailability availability = new DoctorAvailability();
        availability.setDay(DoctorAvailability.Day.MONDAY);
        availability.setDoctor(doctor2);
        availability.setEndTime(LocalTime.MIDNIGHT);
        availability.setId(1L);
        availability.setStartTime(LocalTime.MIDNIGHT);

        // Act
        DoctorAvailability actualAddAvailabilityResult = doctorAvailabilityService.addAvailability(availability);

        // Assert
        assertSame(doctorAvailability, actualAddAvailabilityResult);
    }

    /**
     * Test {@link DoctorAvailabilityService#getAvailabilityByDoctorId(Long)}.
     * <p>
     * Method under test:
     * {@link DoctorAvailabilityService#getAvailabilityByDoctorId(Long)}
     */
    @Test
    @DisplayName("Test getAvailabilityByDoctorId(Long)")
    @Disabled("TODO: Complete this test")
    void testGetAvailabilityByDoctorId() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@1a6d5187 testClass = com.outpatient.service.DiffblueFakeClass243, locations = [], classes = [com.outpatient.service.DoctorAvailabilityService, com.outpatient.repository.DoctorAvailabilityRepository], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@6e6d69a9, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@2fc36d24, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@0, org.springframework.boot.test.web.reactor.netty.DisableReactorResourceFactoryGlobalResourcesContextCustomizerFactory$DisableReactorResourceFactoryGlobalResourcesContextCustomizerCustomizer@749d35f3, org.springframework.boot.test.autoconfigure.OnFailureConditionReportContextCustomizerFactory$OnFailureConditionReportContextCustomizer@7446c746, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@5a7035f8, org.springframework.test.context.support.DynamicPropertiesContextCustomizer@0], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
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
        doctorAvailabilityService.getAvailabilityByDoctorId(1L);
    }

    /**
     * Test {@link DoctorAvailabilityService#getAvailabilityByDoctorName(String)}.
     * <p>
     * Method under test:
     * {@link DoctorAvailabilityService#getAvailabilityByDoctorName(String)}
     */
    @Test
    @DisplayName("Test getAvailabilityByDoctorName(String)")
    @Disabled("TODO: Complete this test")
    void testGetAvailabilityByDoctorName() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@6370f433 testClass = com.outpatient.service.DiffblueFakeClass244, locations = [], classes = [com.outpatient.service.DoctorAvailabilityService, com.outpatient.repository.DoctorAvailabilityRepository], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@6e6d69a9, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@2fc36d24, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@0, org.springframework.boot.test.web.reactor.netty.DisableReactorResourceFactoryGlobalResourcesContextCustomizerFactory$DisableReactorResourceFactoryGlobalResourcesContextCustomizerCustomizer@749d35f3, org.springframework.boot.test.autoconfigure.OnFailureConditionReportContextCustomizerFactory$OnFailureConditionReportContextCustomizer@7446c746, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@5a7035f8, org.springframework.test.context.support.DynamicPropertiesContextCustomizer@0], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
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
        doctorAvailabilityService.getAvailabilityByDoctorName("Doctor Name");
    }
}
