/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE330_Insufficiently_Random_Values__basic_11.java
Label Definition File: CWE330_Insufficiently_Random_Values__basic.label.xml
Template File: point-flaw-11.tmpl.java
*/
/*
* @description
* CWE: 330 Insufficiently Random Values
* Sinks:
*    GoodSink: use SecureRandom
*    BadSink : hardcoded salt
* Flow Variant: 11 Control flow: if(IO.static_returns_t()) and if(IO.static_returns_f())
*
* */

package testcases.CWE330_Insufficiently_Random_Values.s01;

import testcasesupport.*;

import javax.servlet.http.*;
import java.io.*;
import java.security.SecureRandom;
import java.util.Random;

public class CWE330_Insufficiently_Random_Values__basic_11 extends AbstractTestCase
{

    public void bad() throws Throwable
    {
        if (IO.staticReturnsTrue())
        {
            Random rand = new Random();
            /* FLAW: seed is static, making the numbers always occur in the same sequence */
            rand.setSeed(123456);
            IO.writeLine("Random int: " + rand.nextInt(100));
        }
        else {
            /* INCIDENTAL: CWE 561 Dead Code, the code below will never run */

            /* FIX: use SecureRandom to be cryptographically secure */
            SecureRandom rand = new SecureRandom();
            IO.writeLine("Random int: " + rand.nextInt(100));

        }
    }

    /* good1() changes IO.static_returns_t() to IO.static_returns_f() */
    private void good1() throws Throwable
    {
        if(IO.staticReturnsFalse())
        {
            /* INCIDENTAL: CWE 561 Dead Code, the code below will never run */
            Random rand = new Random();
            /* FLAW: seed is static, making the numbers always occur in the same sequence */
            rand.setSeed(123456);
            IO.writeLine("Random int: " + rand.nextInt(100));
        }
        else {

            /* FIX: use SecureRandom to be cryptographically secure */
            SecureRandom rand = new SecureRandom();
            IO.writeLine("Random int: " + rand.nextInt(100));

        }
    }

    /* good2() reverses the bodies in the if statement */
    private void good2() throws Throwable
    {
        if(IO.staticReturnsTrue())
        {
            /* FIX: use SecureRandom to be cryptographically secure */
            SecureRandom rand = new SecureRandom();
            IO.writeLine("Random int: " + rand.nextInt(100));
        }
        else {
            /* INCIDENTAL: CWE 561 Dead Code, the code below will never run */

            Random rand = new Random();
            /* FLAW: seed is static, making the numbers always occur in the same sequence */
            rand.setSeed(123456);
            IO.writeLine("Random int: " + rand.nextInt(100));

        }

    }

    public void good() throws Throwable
    {
        good1();
        good2();
    }

    /* Below is the main(). It is only used when building this testcase on
       its own for testing or for building a binary to use in testing binary
       analysis tools. It is not used when compiling all the testcases as one
       application, which is how source code analysis tools are tested. */
    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}
