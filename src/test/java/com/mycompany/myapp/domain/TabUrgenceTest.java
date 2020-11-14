package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TabUrgenceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TabUrgence.class);
        TabUrgence tabUrgence1 = new TabUrgence();
        tabUrgence1.setId(1L);
        TabUrgence tabUrgence2 = new TabUrgence();
        tabUrgence2.setId(tabUrgence1.getId());
        assertThat(tabUrgence1).isEqualTo(tabUrgence2);
        tabUrgence2.setId(2L);
        assertThat(tabUrgence1).isNotEqualTo(tabUrgence2);
        tabUrgence1.setId(null);
        assertThat(tabUrgence1).isNotEqualTo(tabUrgence2);
    }
}
