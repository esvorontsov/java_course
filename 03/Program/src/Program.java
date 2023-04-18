public class Program {
    public static void main(String[] args) {
        FieldDef[] fields = {
                new FieldDef("model", "String"),
                new FieldDef("speed", "int"),
        };
        String str = generateClassStr("Car", fields);
    }

    private static String generateClassStr(String className, FieldDef[] fields) {
        if (fields.length == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("public class %s {\n\n", className));

        // приватные поля
        for(FieldDef fd : fields) {
            sb.append(String.format("  private %s %s;\n", fd.getFieldType(), fd.getFieldName()));
        }
        sb.append("\n");

        // методы доступа
        for(FieldDef fd : fields) {
            sb.append(String.format("  public %s get%s() {\n", fd.getFieldType(), Capitalize(fd.getFieldName())));
            sb.append(String.format("    return %s;\n", fd.getFieldType(), fd.getFieldName()));
            sb.append("  }\n\n");
            sb.append(String.format("  public %s set%s(%s %s) {\n", fd.getFieldType(), Capitalize(fd.getFieldName()), fd.getFieldType(), fd.getFieldName()));
            sb.append(String.format("    this.%s = %s;\n", fd.getFieldName(), fd.getFieldName()));
            sb.append("  }\n\n");
        }

        sb.append("}\n");
        return sb.toString();
    }

    private static String Capitalize(String str) {
        if (str.length() == 0){
            return str;
        }
        if (str.length() == 1){
            return str.toUpperCase();
        }
        String result = str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
        return result;
    }

}

