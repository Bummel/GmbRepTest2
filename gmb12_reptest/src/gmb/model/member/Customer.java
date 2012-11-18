package gmb.model.member;

import javax.persistence.Entity;

import org.salespointframework.core.user.Capability;

@Entity
public class Customer extends Member 
{
	@Deprecated
	protected Customer() {}

	public Customer(String useridentifier, String name, String password) 
	{
		super(useridentifier, name, password);
		addCapability(new Capability("customer"));
	}
}