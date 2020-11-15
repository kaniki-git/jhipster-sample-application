package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TabRefPaysTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TabRefPays.class);
        TabRefPays tabRefPays1 = new TabRefPays();
        tabRefPays1.setId(1L);
        TabRefPays tabRefPays2 = new TabRefPays();
        tabRefPays2.setId(tabRefPays1.getId());
        assertThat(tabRefPays1).isEqualTo(tabRefPays2);
        tabRefPays2.setId(2L);
        assertThat(tabRefPays1).isNotEqualTo(tabRefPays2);
        tabRefPays1.setId(null);
        assertThat(tabRefPays1).isNotEqualTo(tabRefPays2);
    }
}
