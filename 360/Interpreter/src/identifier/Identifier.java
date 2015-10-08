package identifier;

public class Identifier<T>
{
	private String name;
	private T value;

	public Identifier(String name, T value)
	{
		this.name = name;
		this.value = value;
	}

	public String getName()
	{
		return this.name;
	}

	public T getValue()
	{
		return this.value;
	}
}