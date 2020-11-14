package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TabExamPhysTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TabExamPhys.class);
        TabExamPhys tabExamPhys1 = new TabExamPhys();
        tabExamPhys1.setId(1L);
        TabExamPhys tabExamPhys2 = new TabExamPhys();
        tabExamPhys2.setId(tabExamPhys1.getId());
        assertThat(tabExamPhys1).isEqualTo(tabExamPhys2);
        tabExamPhys2.setId(2L);
        assertThat(tabExamPhys1).isNotEqualTo(tabExamPhys2);
        tabExamPhys1.setId(null);
        assertThat(tabExamPhys1).isNotEqualTo(tabExamPhys2);
    }
}
