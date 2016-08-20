public abstract class Employee implements Comparable<Employee>
{
   private String firstName;
   private String lastName;
   private String socialSecurityNumber;

   // constructor
   public Employee(String firstName, String lastName, 
      String socialSecurityNumber)
   {
      this.firstName = firstName;                                    
      this.lastName = lastName;                                    
      this.socialSecurityNumber = socialSecurityNumber;         
   } 
   
   //copy constructor
   public Employee (Employee e)
   {
	  this.firstName = e.firstName;                                    
      this.lastName = e.lastName;                                    
      this.socialSecurityNumber = e.socialSecurityNumber; 

   }
   
   // return first name
   public String getFirstName()
   {
      return firstName;
   } 

   // return last name
   public String getLastName()
   {
      return lastName;
   } 

   // return social security number
   public String getSocialSecurityNumber()
   {
      return socialSecurityNumber;
   } 
   
   
   	public abstract double earnings();

  
	public int compareTo(Employee other) throws ClassCastException 
	{
		if (!(other instanceof Employee))
			throw new ClassCastException("Not an employee object");
		else
			return Double.valueOf(earnings()).compareTo(Double.valueOf(other.earnings()));
	}

	 public boolean equals (Object otherObject)   
	 {   
	 	
	 	if (otherObject == null) 
	 	{
	 		 return false;
	 	}
	 	
	 	if (!(otherObject instanceof Employee))
	 	{
	 		return false;
	 	}
	 	
	 	Employee other= (Employee) otherObject;
	 	
	 	return (firstName.equals(other.firstName) && lastName.equals(other.lastName) && socialSecurityNumber.equals(other.socialSecurityNumber));
	 	}



}