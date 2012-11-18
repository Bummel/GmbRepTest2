package gmb.model.member;

import javax.persistence.Entity;

import org.salespointframework.core.user.Capability;

@Entity
public class Admin extends Employee 
{
	@Deprecated
	protected Admin() {}

	public Admin(String useridentifier, String name, String password) 
	{
		super(useridentifier, name, password);
		addCapability(new Capability("admin"));
	}
}