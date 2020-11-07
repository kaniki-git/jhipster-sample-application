package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TabGrossesseTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TabGrossesse.class);
        TabGrossesse tabGrossesse1 = new TabGrossesse();
        tabGrossesse1.setId(1L);
        TabGrossesse tabGrossesse2 = new TabGrossesse();
        tabGrossesse2.setId(tabGrossesse1.getId());
        assertThat(tabGrossesse1).isEqualTo(tabGrossesse2);
        tabGrossesse2.setId(2L);
        assertThat(tabGrossesse1).isNotEqualTo(tabGrossesse2);
        tabGrossesse1.setId(null);
        assertThat(tabGrossesse1).isNotEqualTo(tabGrossesse2);
    }
}
