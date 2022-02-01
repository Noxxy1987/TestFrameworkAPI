import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParameterTypes {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @DefaultParameterTransformer
    @DefaultDataTableEntryTransformer(replaceWithEmptyString = {"[blank]","empty"})
    @DefaultDataTableCellTransformer(replaceWithEmptyString = {"[blank]","empty"})
    public Object transformer(Object fromValue, Type toValueType) {
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        return objectMapper.convertValue(fromValue, objectMapper.constructType(toValueType));
    }

    @DataTableType(replaceWithEmptyString = {"[blank]","empty"})
    public String listOfStringListsType(String cell) {
        return cell;
    }

//    @ParameterType(name = "list", value = "\"[\\[\\]\\d_,;:.{} \"“*+=~#$%`\\\\&\\-\\pL\\pM'!()<>\\s™®©?@^\\p{Sc}\\/]+\"")
//    public List<String> list(String strings) {
//        if(removeDoubleQuotes(strings).equals("empty")){
//            return new ArrayList<>();
//        }
//        else {
//            return Arrays.stream(removeDoubleQuotes(strings).split(";")).map(String::trim).collect(Collectors.toList());
//        }
//    }
//
//    @ParameterType(value = "(true|false)")
//    public Boolean bool(String string) {
//        return (Boolean) transformer(string, Boolean.class);
//    }
//
//
//    public static String removeDoubleQuotes(String s) {
//        return s.replaceAll("^\"|\"$", "");
//    }

}
