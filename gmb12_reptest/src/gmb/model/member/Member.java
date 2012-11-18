package gmb.model.member;

import javax.persistence.Entity;

import org.salespointframework.core.user.Capability;
import org.salespointframework.core.user.PersistentUser;
import org.salespointframework.core.user.UserIdentifier;


@Entity
public class Member extends PersistentUser 
{
	//		@SuppressWarnings("unused")
	protected String name;
	protected boolean activated = false;
	
	@Deprecated
	protected Member() {}

	public Member(String useridentifier, String name, String password) 
	{
		super(new UserIdentifier(useridentifier), password);
		addCapability(new Capability("member"));
		
		this.name = name;
	}

	public boolean hasCapability(String capname)
	{
		return this.hasCapability(new Capability(capname));
	}

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public void activate()
	{
		activated = true;
		addCapability(new Capability("activated"));
	}
	
	public boolean getActivated()
	{
		return activated;
	}
}