import java.util.Arrays;
public class PayrollSystemTestQ3 
{
   public static void main(String[] args) 
   {
      // create subclass objects
      SalariedEmployee salariedEmployee = new SalariedEmployee("John", "Smith", "111-11-1111", 800.00);
      System.out.println("*****salariedEmployee*****");
      System.out.println(salariedEmployee);
      System.out.println();
      SalariedEmployee salariedEmployeeDeepCopy = new SalariedEmployee(salariedEmployee);
      System.out.println("*****salariedEmployeeDeepCopy*****");
      System.out.println(salariedEmployeeDeepCopy);
      System.out.println();
      if(salariedEmployee.compareTo(salariedEmployeeDeepCopy) == 0)
	  System.out.println("equals(): SalariedEmployee Deep copy and the original are equal");
      else
	  System.out.printf("equals():SalariedEmployee Deep copy and the original are not equal");

      if(salariedEmployee == salariedEmployeeDeepCopy)
	  System.out.println("==: SalariedEmployee Deep copy and the original are equal");
      else
	  System.out.println("==: SalariedEmployee Deep copy and the original are not equal");
      System.out.println();

      HourlyEmployee hourlyEmployee = new HourlyEmployee("Karen", "Price", "222-22-2222", 16.75, 40);
      System.out.println("*****hourlyEmployee*****");
      System.out.println(hourlyEmployee);
      System.out.println();

      HourlyEmployee hourlyEmployeeDeepCopy = new HourlyEmployee(hourlyEmployee);
      System.out.println("*****hourlyEmployeeDeepCopy*****");
      System.out.println(hourlyEmployeeDeepCopy);
      System.out.println();

      if(hourlyEmployee.compareTo(hourlyEmployeeDeepCopy) == 0)
	  System.out.println("equals(): HourlyEmployee Deep copy and the original equal");
      else
	  System.out.println("equals(): HourlyEmployee Deep copy and the original are not equal");

      if(hourlyEmployee == hourlyEmployeeDeepCopy)
	  System.out.println("==: HourlyEmployee Deep copy and the original are equal");
      else
	  System.out.println("==: HourlyEmployee Deep copy and the original are not equal");
      System.out.println();

      CommissionEmployee commissionEmployee = new CommissionEmployee("Sue", "Jones", "333-33-3333", 10000, .06);
      System.out.println("*****commissionEmployee*****");
      System.out.println(commissionEmployee);
      System.out.println();

      CommissionEmployee commissionEmployeeDeepCopy = new CommissionEmployee(commissionEmployee);
      System.out.println("*****commissionEmployeeDeepCopy*****");
      System.out.println(commissionEmployeeDeepCopy);
      System.out.println();

      if(commissionEmployee.compareTo(commissionEmployeeDeepCopy) == 0)
	  System.out.println("equals: CommissionEmployee Deep copy and the original are equal");
      else
	  System.out.println("equals: CommissionEmployee Deep copy and the original are not equal");

      if(commissionEmployee == commissionEmployeeDeepCopy)
	  System.out.println("==: CommissionEmployee Deep copy and the original are equal");
      else
	  System.out.println("==: CommissionEmployee Deep copy and the original are not equal");
      System.out.println();

      BasePlusCommissionEmployee basePlusCommissionEmployee = new BasePlusCommissionEmployee("Bob", "Lewis", "444-44-4444", 5000, .04, 300);
      System.out.println("*****basePlusCommisionEmployee*****");
      System.out.println(basePlusCommissionEmployee);
      System.out.println();

      BasePlusCommissionEmployee basePlusCommissionEmployeeDeepCopy = new BasePlusCommissionEmployee(basePlusCommissionEmployee);
      System.out.println("basePlusCommissionEmployeeDeepCopy");
      System.out.println(basePlusCommissionEmployeeDeepCopy);
      System.out.println();

      if(basePlusCommissionEmployee.compareTo(basePlusCommissionEmployeeDeepCopy) == 0)
	  System.out.println("equals: BasePlusCommissionEmployee Deep copy and the original are equal");
      else
	  System.out.println("equals: BasePlusCommissionEmployee Deep copy and the original are not equal");

      if(basePlusCommissionEmployee == basePlusCommissionEmployeeDeepCopy)
	  System.out.println("==: BasePlusCommissionEmployee Deep copy and the original are equal");
      else
	  System.out.println("==: BasePlusCommissionEmployee Deep copy and the original are not equal");
      System.out.println();
   } // end main
} // end class PayrollSystemTest