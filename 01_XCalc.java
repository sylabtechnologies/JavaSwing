/*
 * Attach UI to 
 * https://github.com/sylabtechnologies/June_test/blob/master/Codility2/03_polishcalc.cpp
 */
package xcalc;

public class XCalc {

    public static void main(String[] args)
    {
        ModelClass model = new ModelClass();
        CalcSubClass calc = new CalcSubClass(model);
        calc.pack();
    }
    
}
