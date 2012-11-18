package gmb.model.member;

import javax.persistence.Entity;

import org.salespointframework.core.user.Capability;

@Entity
public class Employee extends Member 
{
	@Deprecated
	protected Employee() {}

	public Employee(String useridentifier, String name, String password) 
	{
		super(useridentifier, name, password);
		addCapability(new Capability("employee"));
	}
}
