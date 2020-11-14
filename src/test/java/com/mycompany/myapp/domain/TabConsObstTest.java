package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TabConsObstTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TabConsObst.class);
        TabConsObst tabConsObst1 = new TabConsObst();
        tabConsObst1.setId(1L);
        TabConsObst tabConsObst2 = new TabConsObst();
        tabConsObst2.setId(tabConsObst1.getId());
        assertThat(tabConsObst1).isEqualTo(tabConsObst2);
        tabConsObst2.setId(2L);
        assertThat(tabConsObst1).isNotEqualTo(tabConsObst2);
        tabConsObst1.setId(null);
        assertThat(tabConsObst1).isNotEqualTo(tabConsObst2);
    }
}
