// Employee hierarchy test
import java.util.Arrays;
public class PayrollSystemTestQ4
{
   public static void main(String[] args)
   {
      // create subclass objects
      SalariedEmployee salariedEmployee =
         new SalariedEmployee("John", "Smith", "111-11-1111", 800.00);
      HourlyEmployee hourlyEmployee =
         new HourlyEmployee("Karen", "Price", "222-22-2222", 16.75, 40);
      CommissionEmployee commissionEmployee =
         new CommissionEmployee(
         "Sue", "Jones", "333-33-3333", 10000, .06);
      BasePlusCommissionEmployee basePlusCommissionEmployee =
         new BasePlusCommissionEmployee(
         "Bob", "Lewis", "444-44-4444", 5000, .04, 300);

      // create four-element Employee array
      Employee[] employees = new Employee[4];

      // initialize array with Employees
      employees[0] = salariedEmployee;
      employees[1] = hourlyEmployee;
      employees[2] = commissionEmployee;
      employees[3] = basePlusCommissionEmployee;

      System.out.printf("*******Employees before sorting************ %n%n");

      for (Employee currentEmployee : employees)
      {
         System.out.println(currentEmployee); // invokes toString
	 System.out.printf("earned $%,.2f%n%n", currentEmployee.earnings());
      }

      Arrays.sort(employees);

      System.out.printf("*******Employees after sorting*************  %n%n");
      for (Employee currentEmployee : employees)
      {
         System.out.println(currentEmployee); // invokes toString
	 System.out.printf("earned $%,.2f%n%n", currentEmployee.earnings());
      }

} // end main
} // end class PayrollSystemTest
