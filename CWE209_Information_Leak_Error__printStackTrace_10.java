/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE209_Information_Leak_Error__printStackTrace_10.java
Label Definition File: CWE209_Information_Leak_Error.label.xml
Template File: point-flaw-10.tmpl.java
*/
/*
* @description
* CWE: 209 Information exposure through error message
* Sinks: printStackTrace
*    GoodSink: Print generic error message to console
*    BadSink : Print stack trace to console
* Flow Variant: 10 Control flow: if(IO.staticTrue) and if(IO.staticFalse)
*
* */

package testcases.CWE209_Information_Leak_Error.m00;

import testcasesupport.*;

public class CWE209_Information_Leak_Error__printStackTrace_10 extends AbstractTestCase
{
    public void bad() throws Throwable
    {
        if (IO.staticTrue)
        {
            try
            {
                throw new UnsupportedOperationException();
            }
            catch (UnsupportedOperationException exceptUnsupportedOperation)
            {
                exceptUnsupportedOperation.printStackTrace(); /* FLAW: Print stack trace to console on error */
            }
        }
    }

    /* good1() changes IO.staticTrue to IO.staticFalse */
    private void good1() throws Throwable
    {
        if (IO.staticFalse)
        {
            /* INCIDENTAL: CWE 561 Dead Code, the code below will never run */
            IO.writeLine("Benign, fixed string");
        }
        else
        {

            try
            {
                throw new UnsupportedOperationException();
            }
            catch (UnsupportedOperationException exceptUnsupportedOperation)
            {
                IO.writeLine("There was an unsupported operation error"); /* FIX: print a generic message */
            }

        }
    }

    /* good2() reverses the bodies in the if statement */
    private void good2() throws Throwable
    {
        if (IO.staticTrue)
        {
            try
            {
                throw new UnsupportedOperationException();
            }
            catch (UnsupportedOperationException exceptUnsupportedOperation)
            {
                IO.writeLine("There was an unsupported operation error"); /* FIX: print a generic message */
            }
        }
    }

    public void good() throws Throwable
    {
        good1();
        good2();
    }

    /* Below is the main(). It is only used when building this testcase on
     * its own for testing or for building a binary to use in testing binary
     * analysis tools. It is not used when compiling all the testcases as one
     * application, which is how source code analysis tools are tested.
     */
    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}
