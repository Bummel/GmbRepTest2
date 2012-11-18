package gmb;

import gmb.model.member.Admin;
import gmb.model.member.Customer;
import gmb.model.member.Notary;

import org.salespointframework.core.shop.Shop;
import org.salespointframework.core.user.PersistentUserManager;
import org.springframework.stereotype.Component;

@Component
public class Main 
{
	public Main()
	{
		Shop.INSTANCE.initializePersistent();
		
		PersistentUserManager userManager = new PersistentUserManager();
		
		Admin boss1 = new Admin("boss", "simon", "123");
		boss1.activate();
		userManager.add(boss1);
		
		Customer customer1 = new Customer("tomi", "tom", "123");
		customer1.activate();
		userManager.add(customer1);
		
		Customer customer2 = new Customer("ulf", "ulf", "123");
		userManager.add(customer2);
		
		Customer employee1 = new Customer("timo", "tim", "123");
		employee1.activate();
		userManager.add(employee1);
		
		Notary notary1 = new Notary("kim", "kim", "123");
		notary1.activate();
		userManager.add(notary1);
	}
}
