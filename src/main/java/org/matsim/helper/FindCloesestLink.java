package org.matsim.helper;

import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.network.Node;
import org.matsim.core.network.NetworkUtils;
import org.matsim.core.network.io.MatsimNetworkReader;
import org.matsim.core.utils.geometry.CoordinateTransformation;
import org.matsim.core.utils.geometry.transformations.TransformationFactory;

public class FindCloesestLink {
    public static void main( String[] args){
        String networkPath = "scenarios/HN/LK_HN.net.xml";

        Network network1 = NetworkUtils.createNetwork();
        new MatsimNetworkReader(network1).readFile(networkPath);

        Coord homeCoord = new Coord(9.2195699, 49.1632808);

        Link nearestLink = NetworkUtils.getNearestLink(network1, homeCoord);
        Node nearestNode = NetworkUtils.getNearestNode(network1, homeCoord);
        System.out.println("Closest Link: " + nearestLink.getId());
        System.out.println("Closest Node: " + nearestNode.getCoord());


        CoordinateTransformation ct =
                TransformationFactory.getCoordinateTransformation(TransformationFactory.WGS84, TransformationFactory.WGS84_UTM33N);

        Coord transformedCoord = ct.transform(homeCoord);
        System.out.println("Transformed Coordinates: " + transformedCoord);
    }
}
