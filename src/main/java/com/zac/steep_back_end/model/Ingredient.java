package com.zac.steep_back_end.model;


public class Ingredient {
        private String aromaId;
        private int aromaProportion;
        private int aromaQuantity;  
        
        public Ingredient(){}


        public Ingredient(int aromaProportion, int aromaQuantity, String aromaId) {
			this.aromaProportion = aromaProportion;
			this.aromaQuantity = aromaQuantity;
            this.aromaId = aromaId;
		}


		public String getAromaId() {
            return aromaId;
        }

        public void setAromaId(String aromaId) {
            this.aromaId = aromaId;
        }

        public int getAromaProportion() {
            return aromaProportion;
        }

        public void setAromaProportion(int aromaProportion) {
            this.aromaProportion = aromaProportion;
        }

        public int getAromaQuantity() {
            return aromaQuantity;
        }

        public void setAromaQuantity(int aromaQuantity) {
            this.aromaQuantity = aromaQuantity;
        }

       
    }

