<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <surveillanceData-table
            v-if="surveillanceDatas && surveillanceDatas.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:surveillanceDatas="surveillanceDatas"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-surveillance-datas="getAllSurveillanceDatas"
             >

            </surveillanceData-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/index";

import SurveillanceDataTable from "@/components/SurveillanceDataTable";
import SurveillanceDataService from "../services/SurveillanceDataService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    SurveillanceDataTable,
  },
  data() {
    return {
      surveillanceDatas: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllSurveillanceDatas(sortBy='surveillanceDataId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await SurveillanceDataService.getAllSurveillanceDatas(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.surveillanceDatas.length) {
					this.surveillanceDatas = response.data.surveillanceDatas;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching surveillanceDatas:", error);
        }
        
      } catch (error) {
        console.error("Error fetching surveillanceData details:", error);
      }
    },
  },
  mounted() {
    this.getAllSurveillanceDatas();
  },
  created() {
    this.$root.$on('searchQueryForSurveillanceDatasChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllSurveillanceDatas();
    })
  }
};
</script>
<style></style>
