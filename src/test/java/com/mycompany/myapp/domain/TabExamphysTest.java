package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TabExamphysTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TabExamphys.class);
        TabExamphys tabExamphys1 = new TabExamphys();
        tabExamphys1.setId(1L);
        TabExamphys tabExamphys2 = new TabExamphys();
        tabExamphys2.setId(tabExamphys1.getId());
        assertThat(tabExamphys1).isEqualTo(tabExamphys2);
        tabExamphys2.setId(2L);
        assertThat(tabExamphys1).isNotEqualTo(tabExamphys2);
        tabExamphys1.setId(null);
        assertThat(tabExamphys1).isNotEqualTo(tabExamphys2);
    }
}
