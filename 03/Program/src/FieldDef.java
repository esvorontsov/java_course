import java.util.Map;

public class FieldDef
{
    private String fieldName;
    private String fieldType;

    public FieldDef(String fieldName, String fieldType)
    {
        this.fieldName = fieldName.toLowerCase();
        this.fieldType = fieldType;
    }

    public String getFieldName()
    {
        return this.fieldName;
    }

    public String getFieldType()
    {
        return this.fieldType;
    }
}
