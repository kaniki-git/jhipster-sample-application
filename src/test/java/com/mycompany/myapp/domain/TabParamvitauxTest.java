package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TabParamvitauxTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TabParamvitaux.class);
        TabParamvitaux tabParamvitaux1 = new TabParamvitaux();
        tabParamvitaux1.setId(1L);
        TabParamvitaux tabParamvitaux2 = new TabParamvitaux();
        tabParamvitaux2.setId(tabParamvitaux1.getId());
        assertThat(tabParamvitaux1).isEqualTo(tabParamvitaux2);
        tabParamvitaux2.setId(2L);
        assertThat(tabParamvitaux1).isNotEqualTo(tabParamvitaux2);
        tabParamvitaux1.setId(null);
        assertThat(tabParamvitaux1).isNotEqualTo(tabParamvitaux2);
    }
}
