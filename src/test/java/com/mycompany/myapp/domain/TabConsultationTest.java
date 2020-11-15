package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TabConsultationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TabConsultation.class);
        TabConsultation tabConsultation1 = new TabConsultation();
        tabConsultation1.setId(1L);
        TabConsultation tabConsultation2 = new TabConsultation();
        tabConsultation2.setId(tabConsultation1.getId());
        assertThat(tabConsultation1).isEqualTo(tabConsultation2);
        tabConsultation2.setId(2L);
        assertThat(tabConsultation1).isNotEqualTo(tabConsultation2);
        tabConsultation1.setId(null);
        assertThat(tabConsultation1).isNotEqualTo(tabConsultation2);
    }
}
