import { informationType } from "../../types/informationTypes";
import { isInformationType } from "../../types/informationTypes";
import axiosInstance from "../../utils/axiosConfig";

async function getInfo(token: string, foodId :number|null): Promise<informationType[] | null> {
  try {
    const response = await axiosInstance.get(`https://be-fresh.site/api/containers/sensor${foodId ? '?foodId='+foodId : ''}`, {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    });
    
    const temp = response.data.result;
    // console.log(temp)
    if (Array.isArray(temp) && temp.every(isInformationType)) {
      console.log(temp);
      return temp;
    }
  } catch (error) {
    console.error(error);
  }
  return null;
}

export { getInfo }