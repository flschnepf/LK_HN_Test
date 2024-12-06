package org.matsim.helper;

import org.locationtech.jts.geom.Geometry;
import org.matsim.core.utils.gis.ShapeFileReader;
import org.geotools.data.shapefile.shp.ShapefileReader;
import org.matsim.api.core.v01.network.Network;
import org.matsim.core.network.NetworkUtils;
import org.matsim.core.network.io.MatsimNetworkReader;

import java.util.stream.Collectors;

public class RandomPopulationFromShapefile {
    public static void main ( String[] args){

        String networkPath = "scenarios/HN/LK_HN.net.xml";
        String shapeFilePath = "C:/Users/Anwender/Documents/MATSim/OSM_File/Shapefile/LK_Heilbronn.shp";
        Network network = NetworkUtils.createNetwork();
        new MatsimNetworkReader(network).readFile(networkPath);

        var features = ShapeFileReader.getAllFeatures(shapeFilePath);

        var geometries = features.stream()
                .filter(simpleFeature -> simpleFeature.getAttribute("GEN").equals("Untereisesheim"))
                .map(simpleFeature -> (Geometry)simpleFeature.getDefaultGeometry())
                .collect(Collectors.toList());

        var UntereisesheimGeometry = geometries.get(0);
        System.out.println(UntereisesheimGeometry);
    }

}
