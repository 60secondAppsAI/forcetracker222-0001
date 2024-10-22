import http from "../http-common";

class SurveillanceDataService {
  getAllSurveillanceDatas(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/surveillanceData/surveillanceDatas`, searchDTO);
  }

  get(surveillanceDataId) {
    return this.getRequest(`/surveillanceData/${surveillanceDataId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/surveillanceData?field=${matchData}`, null);
  }

  addSurveillanceData(data) {
    return http.post("/surveillanceData/addSurveillanceData", data);
  }

  update(data) {
  	return http.post("/surveillanceData/updateSurveillanceData", data);
  }
  
  uploadImage(data,surveillanceDataId) {
  	return http.postForm("/surveillanceData/uploadImage/"+surveillanceDataId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new SurveillanceDataService();
