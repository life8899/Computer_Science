package identifier;

import java.util.HashMap;

public class SymbolTable<T>
{
    private HashMap<String, T> symbolTable;

    public SymbolTable()
    {
        this.symbolTable = new HashMap<>(5);
    }

    public void insert(Identifier<T> identifier)
    {
        this.symbolTable.put(identifier.getName(), identifier.getValue());
    }

    public void update(Identifier<T> identifier)
    {
	    this.insert(identifier);
    }

    public boolean contains(Identifier<T> identifier)
    {
        return this.symbolTable.containsKey(identifier.getName());
    }

    public boolean contains(String identifierName)
    {
        return this.symbolTable.containsKey(identifierName);
    }

    public T lookup(String identifierName)
    {
        return this.symbolTable.get(identifierName);
    }
}
