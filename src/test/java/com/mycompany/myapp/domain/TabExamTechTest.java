package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TabExamTechTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TabExamTech.class);
        TabExamTech tabExamTech1 = new TabExamTech();
        tabExamTech1.setId(1L);
        TabExamTech tabExamTech2 = new TabExamTech();
        tabExamTech2.setId(tabExamTech1.getId());
        assertThat(tabExamTech1).isEqualTo(tabExamTech2);
        tabExamTech2.setId(2L);
        assertThat(tabExamTech1).isNotEqualTo(tabExamTech2);
        tabExamTech1.setId(null);
        assertThat(tabExamTech1).isNotEqualTo(tabExamTech2);
    }
}
