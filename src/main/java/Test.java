import org.neo4j.driver.v1.*;

/**
 * Created by shadr on 20/06/2016.
 */
public class Test {
    public static void main(String argsp[]){
        Driver driver = GraphDatabase.driver( "bolt://localhost:7687", AuthTokens.basic( "neo4j", "P@ssw0rd" ) );
        Session session = driver.session();
        session.run( "CREATE (a:Consultant {name:'Shadrach', title:'IT Enterprise Consultant'})" );
        StatementResult result = session.run( "MATCH (a:Consultant) WHERE a.name = 'Shadrach' RETURN a.name AS name, a.title AS title" );
        while ( result.hasNext() )
        {
            Record record = result.next();
            System.out.println( record.get( "title" ).asString() + " " + record.get("name").asString() );
        }
        session.close();
        driver.close();

    }
}
