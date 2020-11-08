package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TabHistoriquePatientTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TabHistoriquePatient.class);
        TabHistoriquePatient tabHistoriquePatient1 = new TabHistoriquePatient();
        tabHistoriquePatient1.setId(1L);
        TabHistoriquePatient tabHistoriquePatient2 = new TabHistoriquePatient();
        tabHistoriquePatient2.setId(tabHistoriquePatient1.getId());
        assertThat(tabHistoriquePatient1).isEqualTo(tabHistoriquePatient2);
        tabHistoriquePatient2.setId(2L);
        assertThat(tabHistoriquePatient1).isNotEqualTo(tabHistoriquePatient2);
        tabHistoriquePatient1.setId(null);
        assertThat(tabHistoriquePatient1).isNotEqualTo(tabHistoriquePatient2);
    }
}
