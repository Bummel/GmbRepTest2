
package muffin;

//import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;

public class Muffin
{
	private Muffin(){}

	public static Object invoke(Object invoker, String method_name, Object overloaded_type, Sprinkles args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, MuffinMethodNotFoundException
	{
		if(invoker == null || method_name == null || overloaded_type == null)
			throw new IllegalArgumentException("NullArgument!");
		
		class OMethod
		{
			public Method method;
			public Class<?> para_type;

			public OMethod(Method method, Class<?> para_type)
			{
				this.method = method;
				this.para_type = para_type;
			}
		}

		final Class<?> invoker_class = invoker.getClass();
		final Class<?> otype_class = overloaded_type.getClass();

		final Method[] methods = invoker_class.getMethods();

		final LinkedList<OMethod> omethods = new LinkedList<OMethod>();

		for (Method method : methods)
		{
			if(method_name.equals(method.getName()))
			{
				//				System.out.println(method.getName());

				final Class<?> param_types[] = method.getParameterTypes();

				if(param_types.length == 2)
				{
					Class<?> second_param = param_types[1];

					if(args == null)
					{
						omethods.add(new OMethod(method, param_types[0]));
					}
					else
					for(;;)
					{
						if(second_param.equals(Sprinkles.class))
						{
							omethods.add(new OMethod(method, param_types[0]));
							break;
						}
						else
							if((second_param=second_param.getSuperclass()) == null)
							{
								break;
							}
					}
				}

				//				final Annotation param_anno[][] = method.getParameterAnnotations();
				//
				//				for(int i = 0; i < param_anno.length; i++)
				//				{
				//					final Annotation annos[] = param_anno[i];
				//
				//					for(Annotation anno : annos)
				//						if(anno.annotationType().equals(Sprinkles.class))
				//						{
				//							System.out.println("Sprinkles found!");
				//							System.out.println(param_types[i].getName());
				//							omethods.add(new OMethod(method, param_types[i]));
				//						}
				//				}
			}
		}

		int upsteps = 0;
		Method imethod = null;
		for(OMethod omethod : omethods)
		{
			//			System.out.println("test...");
			Class<?> temp_otype_class = otype_class;

			//System.out.println(test_type.getSuperclass().toString());
			boolean last_test = false;
			int i=0;
			for(; !(last_test=temp_otype_class.equals(omethod.para_type)) && (temp_otype_class=temp_otype_class.getSuperclass())!=null; ++i){}

			if(last_test && imethod == null || i < upsteps)
			{
				imethod = omethod.method;
				upsteps = i;
			}
		}

		if(imethod == null)
			throw new MuffinMethodNotFoundException();
		
			return imethod.invoke(invoker, overloaded_type, args);
	}

	public static Object invoke(Object invoker, String method_name, Object overloaded_type) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, MuffinMethodNotFoundException
	{
		return invoke(invoker, method_name, overloaded_type, null);
	}
}


