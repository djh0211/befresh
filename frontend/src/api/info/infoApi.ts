import axios from "axios";
import { informationType } from "../../types/informationTypes";
import { isInformationType } from "../../types/informationTypes";

async function getInfo(token: string): Promise<informationType[] | null> {
  try {
    const response = await axios.get('https://be-fresh.site/api/containers/sensor', {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    });

    const temp = response.data.result;
    if (Array.isArray(temp) && temp.every(isInformationType)) {
      console.log(temp); // 여기서 값이 잘 나옴
      return temp;
    }
  } catch (error) {
    console.error(error);
  }
  return null;
}

export { getInfo }