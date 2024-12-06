package org.matsim.helper;

import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.population.*;
import org.matsim.core.api.internal.MatsimWriter;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.api.core.v01.network.Network;
import org.matsim.core.network.NetworkUtils;
import org.matsim.api.core.v01.Scenario;
import org.matsim.core.scenario.ScenarioUtils;

public class GenerateOnePersonPopulation {
    public static void main( String[] args) {


        Config config = ConfigUtils.createConfig();
        Scenario sc = ScenarioUtils.createScenario(config);

        Network network = sc.getNetwork();
        Population population = sc.getPopulation();

        PopulationFactory populationFactory = population.getFactory();

        Person person = populationFactory.createPerson(Id.create("3", Person.class));
        population.addPerson(person);
        // System.out.println("Hier die Person: " + population.getFactory());

        Plan plan = populationFactory.createPlan();

        Coord homeCoord = new Coord(9.189524, 49.1318519);
        Activity activity1 = populationFactory.createActivityFromCoord("home", homeCoord);
        activity1.setEndTime(21600);
        plan.addActivity(activity1);
        plan.addLeg(populationFactory.createLeg("car"));


        Coord workCoord = new Coord(9.4170422, 49.317947);
        Activity activity2 = populationFactory.createActivityFromCoord("work", workCoord);
        activity2.setEndTime(57600);
        plan.addActivity(activity2);
        plan.addLeg(populationFactory.createLeg("car"));

        Activity activity3 = populationFactory.createActivityFromCoord("home", homeCoord);
        plan.addActivity(activity3);
        person.addPlan(plan);

        MatsimWriter popWriter = new PopulationWriter(population, network);
        popWriter.write("./scenarios/HN/pop.xml");
    }
}
