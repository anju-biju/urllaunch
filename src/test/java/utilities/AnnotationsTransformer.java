package utilities;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/***
 * Utility class to execute at runtime by checking the failed test cases and then automatically retry multiple times
 */

public class AnnotationTransformer implements IAnnotationTransformer {

    /****
     * To set Retry analyzer with class name
     * @param annotation
     * @param testClass
     * @param testConstructor
     * @param testMethod
     */

    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod){

        annotation.setRetryAnalyzer(RetryAnalyzer.class);


    }
}
