package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TabPatientTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TabPatient.class);
        TabPatient tabPatient1 = new TabPatient();
        tabPatient1.setId(1L);
        TabPatient tabPatient2 = new TabPatient();
        tabPatient2.setId(tabPatient1.getId());
        assertThat(tabPatient1).isEqualTo(tabPatient2);
        tabPatient2.setId(2L);
        assertThat(tabPatient1).isNotEqualTo(tabPatient2);
        tabPatient1.setId(null);
        assertThat(tabPatient1).isNotEqualTo(tabPatient2);
    }
}
