package no.hvl.dat110.rest.counters;

import com.google.gson.Gson;
import no.hvl.dat110.rest.counters.todo.*;

import java.util.*;

import static spark.Spark.*;

/**
 * Hello world!
 *
 */
public class App {
	
	static Counters counters = null;
	static HashMap<Long, Todo> todoHashMap = null;
	
	public static void main(String[] args) {

		if (args.length > 0) {
			port(Integer.parseInt(args[0]));
		} else {
			port(8080);
		}

		counters = new Counters();
		todoHashMap = new HashMap<>();
		
		after((req, res) -> {
  		  res.type("application/json");
  		});
		
		get("/hello", (req, res) -> "Hello World!");
		
        get("/counters", (req, res) -> counters.toJson());
 
        get("/counters/red", (req, res) -> counters.getRed());

        get("/counters/green", (req, res) -> counters.getGreen());

		get("/todos", (req, res) -> prettyprint(todoHashMap));

		get("/todos/:id", (req, res) -> todoHashMap.get(Long.parseLong(req.params("id"))).toJson());

		post("/todos", (req, res) -> {
			Gson gson = new Gson();

			Todo todo = gson.fromJson(req.body(), Todo.class);

			todoHashMap.put(todo.getId(), todo);

			return todo.toJson();
		});

		put("/todos", (req, res) -> {
			Gson gson = new Gson();

			Todo todo = gson.fromJson(req.body(), Todo.class);

			todoHashMap.put(todo.getId(), todo);

			return todo.toJson();
		});


		delete("/todo/:id", (req, res) -> {
			Todo todo = todoHashMap.remove(Long.parseLong(req.params("id")));

			return todo.toJson();
		});

        // TODO: put for green/red and in JSON
        // variant that returns link/references to red and green counter
        put("/counters", (req,res) -> {
        
        	Gson gson = new Gson();
        	
        	counters = gson.fromJson(req.body(), Counters.class);
        
            return counters.toJson();
        	
        });

    }

	public static String prettyprint(HashMap<Long, Todo> hashMap){
		Set<Long> s = hashMap.keySet();
		StringBuilder stringBuilder = new StringBuilder();
		for (Long l :hashMap.keySet()) {
			stringBuilder.append(hashMap.get(l).toJson());
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}

}
