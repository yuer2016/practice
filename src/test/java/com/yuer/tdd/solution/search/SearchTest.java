package com.yuer.tdd.solution.search;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class SearchTest {
    @Test
    void testSearch() throws IOException {
        String pageContent = "There are certain queer times and occasions "
                + "in this strange mixed affair we call life when a man "
                + "takes this whole universe for a vast practical joke, "
                + "though the wit thereof he but dimly discerns, and more "
                + "than suspects that the joke is at nobody's expense but "
                + "his own.";
        byte[] bytes = pageContent.getBytes();
        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
        // search
        Search search = new Search(stream, "practical joke", "1");
        search.setSurroundingCharacterCount(10);
        search.execute();
        assertThat(search.iserrord()).isFalse();
        List<Match> matches = search.getMatches();
        assertThat(matches.size() >= 1).isTrue();
        Match match = matches.get(0);
        assertThat(match.getSearchString()).isEqualTo("practical joke");
        assertThat(match.getSurroundingContext()).isEqualTo("or a vast practical joke, though t");
        stream.close();
    }
}