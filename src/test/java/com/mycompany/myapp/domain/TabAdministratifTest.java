package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TabAdministratifTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TabAdministratif.class);
        TabAdministratif tabAdministratif1 = new TabAdministratif();
        tabAdministratif1.setId(1L);
        TabAdministratif tabAdministratif2 = new TabAdministratif();
        tabAdministratif2.setId(tabAdministratif1.getId());
        assertThat(tabAdministratif1).isEqualTo(tabAdministratif2);
        tabAdministratif2.setId(2L);
        assertThat(tabAdministratif1).isNotEqualTo(tabAdministratif2);
        tabAdministratif1.setId(null);
        assertThat(tabAdministratif1).isNotEqualTo(tabAdministratif2);
    }
}
