package gmb.model.member;

import javax.persistence.Entity;

import org.salespointframework.core.user.Capability;

@Entity
public class Notary extends Member 
{
	@Deprecated
	protected Notary() {}

	public Notary(String useridentifier, String name, String password) 
	{
		super(useridentifier, name, password);
		addCapability(new Capability("notary"));
	}
}