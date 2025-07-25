
package org.example.restclient.model;

import java.util.List;

public record RickAndMortyResponse(RickAndMortyInfo info, List<RickAndMortyChar> results) {
}
