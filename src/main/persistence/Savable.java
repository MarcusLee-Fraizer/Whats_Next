package persistence;

import org.json.JSONObject;

public interface Savable {

    // EFFECTS: returns this as JSON object
    // Citation: JsonSerializationDemo, VCS link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    JSONObject toJson();

}
