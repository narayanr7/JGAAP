package com.jgaap.distances;

import java.util.HashSet;
import java.util.Set;

import com.jgaap.generics.DistanceCalculationException;
import com.jgaap.generics.DistanceFunction;
import com.jgaap.generics.Event;
import com.jgaap.generics.EventMap;

/**
 * Soergle Distance
 * d = sum( abs(xi - yi) ) / sum( max(xi, yi) )
 * 
 * @author Adam Sargent
 * @version 1.0
 */

public class SoergleDistance extends DistanceFunction {

	@Override
	public String displayName() {
		return "Soergle Distance";
	}

	@Override
	public String tooltipText() {
		return "Soergle Distance";
	}

	@Override
	public boolean showInGUI() {
		return true;
	}

	@Override
	public double distance(EventMap unknownEventMap, EventMap knownEventMap)
			throws DistanceCalculationException {

		Set<Event> events = new HashSet<Event>(unknownEventMap.uniqueEvents());
		events.addAll(knownEventMap.uniqueEvents());

		double distance = 0.0, sumNumer = 0.0, sumDenom = 0.0;
		
		for(Event event : events){
			sumNumer += Math.abs(unknownEventMap.relativeFrequency(event) - knownEventMap.relativeFrequency(event));
			sumDenom += Math.max(unknownEventMap.relativeFrequency(event), knownEventMap.relativeFrequency(event));
		}
		distance = sumNumer / sumDenom;
		
		return distance;
	}

}
