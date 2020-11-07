package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TabHospitalTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TabHospital.class);
        TabHospital tabHospital1 = new TabHospital();
        tabHospital1.setId(1L);
        TabHospital tabHospital2 = new TabHospital();
        tabHospital2.setId(tabHospital1.getId());
        assertThat(tabHospital1).isEqualTo(tabHospital2);
        tabHospital2.setId(2L);
        assertThat(tabHospital1).isNotEqualTo(tabHospital2);
        tabHospital1.setId(null);
        assertThat(tabHospital1).isNotEqualTo(tabHospital2);
    }
}
