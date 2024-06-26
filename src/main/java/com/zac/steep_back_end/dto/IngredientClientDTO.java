package com.zac.steep_back_end.dto;

import com.zac.steep_back_end.model.Ingredient;

public class IngredientClientDTO extends Ingredient {
        private String aromaName;
        private String aromaBrand;
        private String brandId;

        public IngredientClientDTO(){}

		public IngredientClientDTO(String aromaName, String aromaBrand, String brandId) {
			this.aromaName = aromaName;
			this.aromaBrand = aromaBrand;
			this.brandId = brandId;
		}

		public IngredientClientDTO(int aromaProportion, int aromaQuantity, String aromaId, String aromaName,
				String aromaBrand, String brandId) {
			super(aromaProportion, aromaQuantity, aromaId);
			this.aromaName = aromaName;
			this.aromaBrand = aromaBrand;
			this.brandId = brandId;
		}

		public String getAromaName() {
			return aromaName;
		}

		public void setAromaName(String aromaName) {
			this.aromaName = aromaName;
		}

		public String getAromaBrand() {
			return aromaBrand;
		}

		public void setAromaBrand(String aromaBrand) {
			this.aromaBrand = aromaBrand;
		}

		public String getBrandId() {
			return brandId;
		}

		public void setBrandId(String brandId) {
			this.brandId = brandId;
		};

        

}
