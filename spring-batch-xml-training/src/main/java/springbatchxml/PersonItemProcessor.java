package springbatchxml;

import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    @Override
    public Person process(Person person) throws Exception {

        System.out.println("Processing: " + person);

        return person;
    }
}
